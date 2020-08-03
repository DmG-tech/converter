package dmg.converter.repository.datajpa;

import dmg.converter.entity.Conversion;
import dmg.converter.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataJpaConversionRepository implements ConversionRepository {

    @Autowired
    private CrudConversionRepository crudConversionRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Conversion save(Conversion conversion, int userId) {
        if (!conversion.isNew() && get(conversion.getId(), userId) == null) {
            return null;
        }
        conversion.setUser(crudUserRepository.getOne(userId));
        return crudConversionRepository.save(conversion);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudConversionRepository.delete(id, userId) != 0;
    }

    @Override
    public Conversion get(int id, int userId) {
        return crudConversionRepository.findById(id)
                .filter(meal -> meal.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Conversion> getByDate(LocalDate date, int userId) {
        return crudConversionRepository.getAll(userId).stream()
                .filter(conversion -> conversion.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Conversion> getAll(int userId) {
        return crudConversionRepository.getAll(userId);
    }
}
