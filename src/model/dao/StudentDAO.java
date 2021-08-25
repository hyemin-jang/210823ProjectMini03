package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.domain.Student;
import model.domain.Study;
import util.PublicCommon;

public class StudentDAO {
	public static StudentDAO instance = new StudentDAO();

	public StudentDAO() {
	}

	public static StudentDAO getInstance() {
		return instance;
	}

	/** 모든 수강생 검색 */
	public List<Student> getAllStudent() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		List<Student> allStudentList = null;

		try {
			allStudentList = em.createNamedQuery("getAllStudent").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return allStudentList;
	}

	/** 검색조건으로 수강생 검색 - 수강생ID로 - 언제나 1명만 출력 */
	public Student getStudentById(int studentId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		
		Student result = null;
		
		try {
			result = (Student) em.createNamedQuery("Student.findBystudentId").setParameter("studentId", studentId)
				.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

	/** 검색조건으로 수강생 검색 - 이름으로 */
	public List<Student> getStudentByName(String studentName) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		List<Student> result = null;
		
		try {
			result = (List<Student>) em.createNamedQuery("Student.findBystudentName").setParameter("studentName", studentName)
				.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

	/** 검색조건으로 수강생 검색 - 전공으로 */
	public List<Student> getStudentByMajor(String major) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		List<Student> result = new ArrayList<>();
		
		try {
			result = (List<Student>) em.createNamedQuery("Student.findBymajor").setParameter("major", major).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

	/** 검색조건으로 수강생 검색 - 스터디ID로 */
	public List<Student> getStudentByStudyId(Study studyId) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		List<Student> result = new ArrayList<>();
		
		try {
			result = (List<Student>) em.createNamedQuery("Student.findBystudyId").setParameter("studyId", studyId)
				.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

	/** 수강생정보 업데이트 - 주소변경 */
	public boolean updateStudentAddress(int studentId, Object info) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		boolean result = false;
		String address = (String) info;
		Student student = null;
		
		try {
			student = (Student) em.createNamedQuery("Student.findBystudentId").setParameter("studentId", studentId)
					.getSingleResult();
			student.setAddress(address);
			
			tx.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

	/** 수강생정보 업데이트 - 전공변경 */
	public boolean updateStudentMajor(int studentId, Object info) throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		boolean result = false;
		String major = (String) info;
		Student student = null;

		try {
			student = (Student) em.createNamedQuery("Student.findBystudentId").setParameter("studentId", studentId)
					.getSingleResult();
			student.setMajor(major);
			
			tx.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

	/** 수강생정보 업데이트 - 스터디ID변경 */
	public boolean updateStudentStudyId(int studentId, Object info) throws SQLException, NullPointerException {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		boolean result = false;
		Study study = new Study();
		Student student = null;
		
		if(info == null) {
			study = null;
		}else {
			study.setStudyId((int) info);
		}

		try {
			student = (Student) em.createNamedQuery("Student.findBystudentId").setParameter("studentId", studentId)
					.getSingleResult();
			student.setStudyId(study);
			
			tx.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return result;
	}

}
