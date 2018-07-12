package hibernate;

import hibernate.channel.ChannelDAO;
import hibernate.channel.ChannelDAOimpl;
import hibernate.content.ContentDAO;
import hibernate.content.ContentDAOimpl;
import hibernate.message.MessageDAO;
import hibernate.message.MessageDAOimpl;
import hibernate.point.PointDAO;
import hibernate.point.PointDAOimpl;
import hibernate.person.PersonDAO;
import hibernate.person.PersonDAOimpl;

public class Factory {
    private static PersonDAO personDAO = null;
    private static PointDAO pointDAO = null;
    private static ChannelDAO channelDAO = null;
    private static ContentDAO contentDAO=null;
    private static MessageDAO messageDAO=null;
    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public  static MessageDAO getMessageDAO(){
        if (messageDAO==null)
        {
            messageDAO=new MessageDAOimpl();
        }
        return  messageDAO;
    }

    public static ContentDAO getContentDAO(){
        if (contentDAO==null)
        {
            contentDAO=new ContentDAOimpl();
        }
        return contentDAO;
    }

    public static PersonDAO getPersonDAO() {
        if (personDAO == null) {
            personDAO = new PersonDAOimpl();
        }
        return personDAO;
    }

    public static PointDAO getPointDAO() {
        if (pointDAO == null) {
            pointDAO = new PointDAOimpl();
        }
        return pointDAO;
    }

    public static ChannelDAO getChannelDAO() {
        if (channelDAO == null) {
            channelDAO = new ChannelDAOimpl();
        }
        return channelDAO;
    }

}
