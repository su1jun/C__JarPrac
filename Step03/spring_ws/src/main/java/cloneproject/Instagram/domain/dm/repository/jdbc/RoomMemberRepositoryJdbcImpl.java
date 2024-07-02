package cloneproject.Instagram.domain.dm.repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.RequiredArgsConstructor;

import cloneproject.Instagram.domain.dm.entity.Room;
import cloneproject.Instagram.domain.member.entity.Member;

@RequiredArgsConstructor
public class RoomMemberRepositoryJdbcImpl implements RoomMemberRepositoryJdbc {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public void saveAllBatch(Room room, List<Member> members) {
		final String sql = "INSERT INTO room_members (`member_id`, `room_id`) VALUES(?, ?)";

		jdbcTemplate.batchUpdate(
			sql,
			new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, members.get(i).getId().toString());
					ps.setString(2, room.getId().toString());
				}

				@Override
				public int getBatchSize() {
					return members.size();
				}
			});
	}

}
