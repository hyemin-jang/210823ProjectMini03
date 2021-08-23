-- student insert
insert into student values(student_idx.NEXTVAL, '임우송', '경기도 화성', '부동산학과', null);
insert into student values(student_idx.NEXTVAL, '서주연', '경기도 성남', '경찰행정학과', null);
insert into student values(student_idx.NEXTVAL, '장혜민', '서울 강서구', '언론홍보학과', null);

-- study insert
insert into study values(study_idx.NEXTVAL, '파이썬 알고리즘', '파이썬으로 알고리즘 뿌시기', 1, '수');
insert into study values(study_idx.NEXTVAL, '자바 알고리즘', '자바로 알고리즘 뿌시기', 2, '월');

-- attendance insert
insert into attendance values(attendance_idx.NEXTVAL, 1, 33, 0, 0);
insert into attendance values(attendance_idx.NEXTVAL, 2, 32, 1, 0);
insert into attendance values(attendance_idx.NEXTVAL, 3, 31, 1, 1);

commit;
