package delivery.model.dao.mapper;

import delivery.model.entity.AvailableOption;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AvailableOptionMapper implements ObjectMapper<AvailableOption> {
    @Override
    public AvailableOption extractFromResultSet(ResultSet rs) throws SQLException {
        AvailableOption availableOption = new AvailableOption();
        availableOption.setId(rs.getLong("id_option"));
        availableOption.setAvailable(rs.getBoolean("is_available"));
        return availableOption;
    }

    @Override
    public AvailableOption makeUnique(Map<Long, AvailableOption> cache, AvailableOption availableOption) {
        cache.putIfAbsent(availableOption.getId(), availableOption);
        return cache.get(availableOption.getId());
    }
}
