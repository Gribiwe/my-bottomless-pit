package hibernate.channel;

import database.Channel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nikita on 2018/04/27.
 */
public interface ChannelDAO {

    void addChannel(Channel channel) throws SQLException;

    void updateChannel(Channel channel) throws SQLException;

    Channel getChannelById(int id) throws SQLException;

    List getAllChannels() throws SQLException;

    void deleteChannel(Channel channel) throws SQLException;

}
