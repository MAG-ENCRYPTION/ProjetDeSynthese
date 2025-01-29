package ProjetDeSyntheseNotification.notification.service;

import ProjetDeSyntheseNotification.notification.model.Group;
import ProjetDeSyntheseNotification.notification.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public Group updateGroup(Long id, Group updatedGroup) {
        return groupRepository.findById(id).map(group -> {
            group.setGroupName(updatedGroup.getGroupName());
            group.setMessage(updatedGroup.getMessage());
            return groupRepository.save(group);
        }).orElseThrow(() -> new RuntimeException("Group not found with id " + id));
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
