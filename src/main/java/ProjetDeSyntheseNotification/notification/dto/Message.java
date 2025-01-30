package ProjetDeSyntheseNotification.notification.dto;

import java.time.Instant;

public class Message {
    private String messageContent;
    private String status;
    private ResponseData data;

    // Getters et setters pour messageContent
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    // Getters et setters pour status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // Getters et setters pour data
    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }

    // Classe interne pour les données de réponse
    public static class ResponseData {
        private String requestId;
        private String timestamp;
        private Details details;

        public ResponseData(String requestId) {
            this.requestId = requestId;
            this.timestamp = Instant.now().toString(); // Génère le timestamp actuel
            this.details = new Details(); // Vous pouvez remplir cette partie si nécessaire
        }

        // Getters et setters pour requestId
        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        // Getters et setters pour timestamp
        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        // Getters et setters pour details
        public Details getDetails() {
            return details;
        }

        public void setDetails(Details details) {
            this.details = details;
        }

        // Classe interne pour les détails supplémentaires
        public static class Details {
            // Vous pouvez ajouter des informations supplémentaires ici si nécessaire
            private String additionalInfo;

            public String getAdditionalInfo() {
                return additionalInfo;
            }

            public void setAdditionalInfo(String additionalInfo) {
                this.additionalInfo = additionalInfo;
            }
        }
    }
}
