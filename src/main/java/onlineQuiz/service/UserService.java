package onlineQuiz.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import onlineQuiz.config.HibernateConfigUtil;
import onlineQuiz.dao.UserDAO;
import onlineQuiz.domain.UserInfo;
import onlineQuiz.pojo.UserInfoPOJO;

import java.util.List;

public class UserService {

    public static UserInfo getUser(String username, String password) {
        Session session = HibernateConfigUtil.getCurrentSession();
        Transaction transaction = null;
        UserDAO userInfoDAO = new UserDAO();

        UserInfo userInfo = null;

        try {

            transaction = session.beginTransaction();

            userInfo = userInfoDAO.getUser(username, password, session);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return userInfo;
    }

    public static UserInfo getUserById(int userId, boolean currentSession) {

        Session session = null;
        if(currentSession) session = HibernateConfigUtil.getCurrentSession();
        else session = HibernateConfigUtil.openSession();

        Transaction transaction = null;
        UserDAO userInfoDAO = new UserDAO();

        UserInfo userInfo = null;

        try {

            transaction = session.beginTransaction();

            userInfo = userInfoDAO.getUserById(userId, session);
            userInfo.getUserSubmissions().size();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return userInfo;
    }

    public static List<UserInfo> getUsers() {
        Session session = HibernateConfigUtil.getCurrentSession();
        Transaction transaction = null;
        UserDAO userInfoDAO = new UserDAO();

        List<UserInfo> userInfos = null;

        try {

            transaction = session.beginTransaction();

            userInfos = userInfoDAO.getUsers(session);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return userInfos;
    }

    public static boolean setUser(UserInfoPOJO userInfoPOJO) {
        Session session = HibernateConfigUtil.getCurrentSession();
        Transaction transaction = null;
        boolean res = true;

        try {

            transaction = session.beginTransaction();

            UserInfo userInfo = new UserInfo();

            userInfo.setUserName(userInfoPOJO.getUserName());
            userInfo.setUserPassword(userInfoPOJO.getUserPassword());
            userInfo.setUserStatus(userInfoPOJO.getUserStatus());
            userInfo.setFirstName(userInfoPOJO.getFirstName());
            userInfo.setLastName(userInfoPOJO.getLastName());
            userInfo.setPhone(userInfoPOJO.getPhone());
            userInfo.setAddress(userInfoPOJO.getAddress());
            userInfo.setGroupID(userInfoPOJO.getGroupID());

            session.merge(userInfo);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            res = false;
            e.printStackTrace();
        } finally {
            session.close();
        }

        return res;
    }

    public static boolean updateUserStatus(int userId, int userStatus) {
        Session session = HibernateConfigUtil.getCurrentSession();
        Transaction transaction = null;

        boolean success = true;

        try {
            transaction = session.beginTransaction();

            UserInfo userInfo = getUserById(userId, false);
            userInfo.setUserStatus(userStatus);

            session.merge(userInfo);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            success = false;
            e.printStackTrace();
        } finally {
            session.close();
        }

        return success;
    }
}
