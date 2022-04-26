package accident.repositories;

import accident.models.Accident;
import accident.models.AccidentType;
import accident.models.Rule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class AccidentJdbcTemplate implements Store {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Accident create(Accident accident, String[] ids) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into accident(name, text, address, type_id) values(?, ?, ?, ?)",
                    new String[]{"id"});
            statement.setString(1, accident.getName());
            statement.setString(2, accident.getText());
            statement.setString(3, accident.getAddress());
            statement.setInt(4, accident.getType().getId());
            return statement;
        }, keyHolder);
        accident.setId((int) keyHolder.getKey());
        for (String id : ids) {
            jdbc.update("insert into accidents_rules (accident_id, rule_id) " +
                            "values (?, ?) ",
                    accident.getId(),
                    Integer.parseInt(id));
        }
        return accident;
    }

    @Override
    public void update(Accident accident) {
        jdbc.update("update accident set name = ?, text = ?, address = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId()
        );
    }

    @Override
    public Accident get(int id) {
        return jdbc.queryForObject("select * from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(getAccidentType(rs.getInt("type_id")));
                    accident.setRules(new HashSet<>(setRulesForAccident(accident)));
                    return accident;
                }, id);
    }

    @Override
    public List<Accident> getAll() {
        return jdbc.query("select * from accident order by id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(getAccidentType(rs.getInt("type_id")));
                    accident.setRules(new HashSet<>(setRulesForAccident(accident)));
                    return accident;
                });
    }

    @Override
    public Collection<AccidentType> getAllAccidentType() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    @Override
    public Collection<Rule> getAllRules() {
        return jdbc.query("select id, name from accident_rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    private AccidentType getAccidentType(int id) {
        return jdbc.queryForObject("select id, name from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                }, id);
    }

    private Collection<Rule> setRulesForAccident(Accident accident) {
        return jdbc.query(
                "select rules.id, rules.name " +
                        "from accidents_rules as ar " +
                        "inner join accident_rule as rules " +
                        "on ar.rule_id = rules.id and accident_id = ?;",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, accident.getId());
    }
}