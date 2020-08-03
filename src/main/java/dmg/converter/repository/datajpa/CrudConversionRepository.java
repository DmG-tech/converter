package dmg.converter.repository.datajpa;

import dmg.converter.entity.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudConversionRepository extends JpaRepository<Conversion, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Conversion c WHERE c.id=:id AND c.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT c FROM Conversion c WHERE c.user.id=:userId ORDER BY c.id")
    List<Conversion> getAll(@Param("userId") int userId);
}
