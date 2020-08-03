package dmg.converter.repository;


import dmg.converter.entity.Conversion;

import java.time.LocalDate;
import java.util.List;

public interface ConversionRepository {
    // null if not found, when updated
    Conversion save(Conversion conversion, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Conversion get(int id, int userId);

    // emptyList if not found
    List<Conversion> getByDate(LocalDate date, int userId);

    List<Conversion> getAll(int userId);
}
