package com.revature.caliber.assessments.data.implementations;

import com.revature.caliber.assessments.beans.QCNote;
import com.revature.caliber.assessments.data.QCNoteDAO;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for QCNote DAO
 */
public class QCNoteDAOImplementationTest {
    private static ApplicationContext context;
    private static SessionFactory sf;
    private static Logger logger;

    private static int newQCNoteId, weekId = 2828, traineeId = 2828;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");


        //create transient SQL test data
        sf = (SessionFactory) context.getBean("sessionFactory");
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(QCNote.class);

        List<QCNote> list = criteria.list();

        list.sort(Comparator.comparingInt(QCNote::getNoteId));

        //find empty id
        int id = 1;
        for (QCNote note : list) {
            if (note.getNoteId() == id) { id++; }
        }
        newQCNoteId = id;

        String sql = "";
        int resultNum = 0;

        sql = "INSERT INTO CALIBER_NOTE(NOTE_ID, NOTE_CONTENT, NOTE_SUGAR)" +
                " VALUES (?, ?, ?)";

        Query noteq = session.createSQLQuery(sql);
        noteq.setInteger(0, newQCNoteId);
        noteq.setString(1, "content");
        noteq.setString(2, "sugar");

        resultNum = noteq.executeUpdate();

        if (resultNum != 1) {
            tx.commit();
            session.close();
            fail("Failed to create test Note");
        }

        sql = "INSERT INTO CALIBER_QC_NOTE (TRAINEE_ID, WEEK_ID, NOTE_ID) " +
                "VALUES(?, ?, ?)";

        Query qcnoteq = session.createSQLQuery(sql);
        qcnoteq.setInteger(0, traineeId);
        qcnoteq.setInteger(1, weekId);
        qcnoteq.setInteger(2, newQCNoteId);

        resultNum = qcnoteq.executeUpdate();

        if (resultNum != 1) {
            tx.commit();
            session.close();
            fail("Failed to create test QCNote");
        }

        tx.commit();
        session.close();

        logger = Logger.getRootLogger();
        logger.debug("\n--- QCNOTE DAO IMPLEMENTATION TEST START ---\n");
    }

    @AfterClass
    public static void afterClass() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String sql = "";
        Query q;

        sql = "DELETE FROM CALIBER_QC_NOTE WHERE NOTE_ID = ?";
        q = session.createSQLQuery(sql);
        q.setInteger(0, newQCNoteId);
        q.executeUpdate();

        sql = "DELETE FROM CALIBER_NOTE WHERE NOTE_ID = ?";
        q = session.createSQLQuery(sql);
        q.setInteger(0, newQCNoteId);
        q.executeUpdate();

        tx.commit();
        session.close();

        logger.debug("\n--- QCNOTE DAO IMPLEMENTATION TEST END ---\n");
    }

    @Test
    public void testCreate(){
        logger.debug("   Create QCNote test.");
        QCNoteDAO dao = context.getBean(QCNoteDAO.class);

        QCNote note = new QCNote();
        note.setContent("Some test content (QCNote DAO Test)");
        note.setSugarCoatedContent("Some sugar content");
        note.setTrainee(2829);
        note.setWeek(2829);

        dao.createQCNote(note);

        //Test if it was created
        Session session = ((SessionFactory) context.getBean("sessionFactory")).openSession();
        Criteria criteria = session.createCriteria(QCNote.class);
        criteria.add(Restrictions.eq("content", "Some test content (QCNote DAO Test)"));
        QCNote newnote = (QCNote) criteria.uniqueResult();
        assertEquals(note.getNoteId(), newnote.getNoteId());
        assertEquals(note.getContent(), newnote.getContent());

        //cleanup
        Transaction tx = session.beginTransaction();
        session.delete(newnote);
        tx.commit();
        session.close();

        logger.debug("      QCNote created");
    }

    @Test
    public void testGetById() {

        QCNoteDAO dao = context.getBean(QCNoteDAO.class);

        QCNote note = dao.getQCNoteById(newQCNoteId);

        assertNotNull(note);
        assertEquals(newQCNoteId, note.getNoteId());

        logger.debug("     got qc note with id: " + note.getNoteId() + ", and content: " + note.getContent());
    }

    @Test
    public void testGetByBothIds() {
        logger.debug("   Get QCNote by both ids test.");
        QCNoteDAO dao = context.getBean(QCNoteDAO.class);

        QCNote note = dao.getQCNoteForTraineeWeek(traineeId, weekId);
        assertNotNull(note);
        assertEquals(newQCNoteId, note.getNoteId());
        logger.debug("     got note with id: " + note.getNoteId());
    }

    @Test
    public void testGetByTrainee() {
        logger.debug("   Get by trainee test.");
        QCNoteDAO dao = context.getBean(QCNoteDAO.class);

        List<QCNote> list = dao.getQCNotesByTrainee(traineeId);
        assertNotEquals(0, list.size());
        logger.debug("     list size: " + list.size());
    }

    @Test
    public void testGetByWeek() {
        logger.debug("   Get by week test.");
        QCNoteDAO dao = context.getBean(QCNoteDAO.class);
        List<QCNote> list = dao.getQCNotesByWeek(weekId);
        assertNotEquals(0, list.size());
        logger.debug("     list size: " + list.size());
    }

    @Test
    public void testUpdateNote() {
        logger.debug("   Update note test.");
        QCNoteDAO dao = context.getBean(QCNoteDAO.class);

        QCNote note = dao.getQCNoteById(newQCNoteId);
        assertNotNull(note);
        note.setTrainee(8282);

        dao.updateQCNote(note);

        note = dao.getQCNoteById(newQCNoteId);
        assertNotNull(note);
        assertNotEquals(traineeId, note.getTrainee());
        logger.debug("     successfully changed trainee id from " + traineeId + " to " + 8282);
        traineeId = 8282; //NOT A BUG, THESE TESTS CAN'T RUN IN PARALLEL ANYWAY (EACH TEST LOCKS THE DB).
    }

    @Test
    public void testDeleteNote() {
        logger.debug("   Delete QCNote test.");

        //create a test note first
        QCNote note = new QCNote();
        note.setContent("Some test content 2(QCNote DAO Test)");
        note.setSugarCoatedContent("Some sugar content");
        note.setTrainee(2829);
        note.setWeek(2829);

        //then create that note
        Session session = ((SessionFactory) context.getBean("sessionFactory")).openSession();
        Transaction tx = session.beginTransaction();
        session.save(note);
        tx.commit();


        QCNoteDAO dao = context.getBean(QCNoteDAO.class);

        dao.deleteQCNote(note);

        //test if it was deleted
        Criteria criteria = session.createCriteria(QCNote.class);
        criteria.add(Restrictions.eq("content", "Some test content 2(QCNote DAO Test)"));
        note = (QCNote) criteria.uniqueResult();
        session.close();
        assertNull(note);

        logger.debug("     note was successfully deleted.");

    }

}
