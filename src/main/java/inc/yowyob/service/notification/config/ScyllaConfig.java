package inc.yowyob.service.notification.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class ScyllaConfig {

    private static final String KEYSPACE = "yowyobdbnotification";
    private static final String DATA_CENTER = "datacenter1";

    public ScyllaConfig() {
        try (CqlSession session = CqlSession.builder()
                .withKeyspace(KEYSPACE)
                .withLocalDatacenter(DATA_CENTER)
                .build()) {

            if (!keyspaceExists(session, KEYSPACE)) {
                executeCQLFile(session, "schema.cql");
            } else {
                System.out.println("Keyspace existe déjà. Mise à jour des tables...");
                updateTables(session);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean keyspaceExists(CqlSession session, String keyspace) {
        String query = "SELECT keyspace_name FROM system_schema.keyspaces WHERE keyspace_name = ?;";
        return session.execute(session.prepare(query).bind(keyspace)).one() != null;
    }

    private void executeCQLFile(CqlSession session, String fileName) throws IOException {
        Path path = new ClassPathResource(fileName).getFile().toPath();
        List<String> lines = Files.readAllLines(path);
        StringBuilder cql = new StringBuilder();

        for (String line : lines) {
            if (!line.trim().isEmpty() && !line.startsWith("--")) {
                cql.append(line).append(" ");
                if (line.trim().endsWith(";")) {
                    session.execute(cql.toString());
                    cql.setLength(0);
                }
            }
        }
        System.out.println("Keyspace et tables créés.");
    }

    private void updateTables(CqlSession session) {
        // Recréer les tables sans toucher aux données existantes
        List<String> tableUpdates = List.of(
                "CREATE TABLE IF NOT EXISTS email_notification (email_id UUID PRIMARY KEY, priority FLOAT, reciever_email TEXT, type TEXT, message TEXT, subject TEXT);",
                "CREATE TABLE IF NOT EXISTS sms_notifications (notification_id UUID PRIMARY KEY, number TEXT, message TEXT, type TEXT, priority FLOAT);",
                "CREATE TABLE IF NOT EXISTS push_notifications (push_notifications_id UUID PRIMARY KEY, sender_name TEXT, receiver_ids LIST<UUID>, group_name TEXT, priority INT, property_type TEXT, receiver_name TEXT, message TEXT, push_notification_type TEXT, subject TEXT, timestamp TIMESTAMP);",
                "CREATE TABLE IF NOT EXISTS whatsapp_notifications (whatsapp_notification_id UUID PRIMARY KEY, recipient TEXT, message TEXT);"
        );

        for (String query : tableUpdates) {
            session.execute(query);
        }
        System.out.println("Mise à jour des tables terminée.");
    }
}
