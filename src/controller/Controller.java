package controller;

import java.sql.SQLException;

import javax.persistence.NoResultException;

import org.hibernate.PersistentObjectException;

import service.Service;
import view.EndView;

public class Controller {
	private static Controller instance = new Controller();
	private static Service service = Service.getInstance();
			
	public Controller() {}
	public static Controller getInstance() {
		return instance;
	}
	
	
		/** 모든 수강생 검색 */
		public static void getAllStudent(){
			
			try {
				EndView.showAllList(service.getAllStudents());
			}catch(SQLException e){
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}catch(NullPointerException e){
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}catch(NoResultException e){
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}
		}

		/** 검색조건으로 수강생 검색 */ 
		public void getSearchedStudent(int searchNo, Object info){
			try {
				if(searchNo == 1) {
					EndView.showOne(service.getOneStudents(info));
				}else{
					EndView.showAllList(service.getSearchedStudents(searchNo, info));
				}
			}catch(SQLException e){
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}catch(NullPointerException e){
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}catch(NoResultException e){
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}
		}
		
		/** 수강생정보 업데이트 */ 
		public void updateStudent(int searchNo, int studentId, Object info) {
			boolean result = false;
			try {
				result = service.updateStudent(searchNo, studentId, info);
				if(result == false) {
					EndView.showError("잘못된 정보를 입력하셨습니다");
				}else {
					EndView.showMessage("변경에 성공했습니다.");
				}
			}catch(SQLException e){
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}catch(PersistentObjectException e){
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}
		}
		
		/** 수강생ID로 수강생정보+출석정보 삭제 */ 
		public void deleteStudent(int studentId) {
			boolean result = false;
			try {
				result = service.deleteStudent(studentId);
				if(result == false) {
					EndView.showError("잘못된 정보를 입력하셨습니다");
				}else {
					EndView.showMessage("삭제 성공했습니다.");
				}
			}catch(SQLException e){
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}catch(PersistentObjectException e){
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}
		}
	
		/** 모든 스터디 검색 */	
		public void getAllStudy() {		
			try {
				EndView.showAllList(service.getAllStudy());
			} catch (SQLException e) {
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			} catch (NullPointerException e) {
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}
	
		}
		
		/** 스터디 id로 스터디 검색 
		 * @param id */	
		public void getStudyById(int id) {
			try {
				EndView.showOne(service.getStudyById(id));
			}catch (SQLException e) {
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			} catch (NullPointerException e) {
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}
			
		}
		
		/** 스터디 주제로 스터디 검색 */
		public void getStudyByTopic(String keyword) {
			try {
				EndView.showAllList(service.getStudyByTopic(keyword));
			}catch (SQLException e) {
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			} catch (NullPointerException e) {
				e.printStackTrace();
				EndView.showError("잘못된 정보를 입력하셨습니다");
			}
			
		}
		
		/** 스터디 추가 */
		public void addStudy(String studyName, String topic, int studentId, String meetingDate) {
			try {
				service.addStudy(studyName, topic, studentId, meetingDate);
				EndView.showMessage("스터디 저장에 성공했습니다.");
			} catch (SQLException e) {
				e.printStackTrace();
				EndView.showError("스터디 저장에 실패했습니다.");
			} catch (NullPointerException e) {
				EndView.showError("스터디 저장에 실패했습니다.");
			}
			
		}
			
	/** 스터디 정보 변경 - 날짜 변경 */
	public void updateStudy(int id, String meetingDate) {
		try {
			service.updateStudy(id, meetingDate);
			EndView.showMessage("변경에 성공했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			EndView.showError("변경에 실패했습니다.");
		} catch (NullPointerException e) {
			EndView.showError("변경에 실패했습니다.");
		}
	}
	
	/** 스터디 삭제 */
	public void deleteStudy(int id) {
		try {
			service.deleteStudy(id);
			EndView.showMessage("삭제에 성공했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			EndView.showError("변경에 실패했습니다.");
		} catch (NullPointerException e) {
			EndView.showError("변경에 실패했습니다.");
		}	
	}
	
}
