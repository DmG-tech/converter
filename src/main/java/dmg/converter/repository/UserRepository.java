package dmg.converter.repository;

import dmg.converter.entity.User;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // null if not found
    User get(int id);

    // null if not found
    User getByUsername(String username);
}
