package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AuthorDao authorDao = new AuthorDao();
		//Insert
		
		AuthorVo vo1 = new AuthorVo("정우성", "영화배우");
		AuthorVo vo2 = new AuthorVo("박경리", "경상남도 통영");
		AuthorVo vo3 = new AuthorVo("유시민", "17대 국회의원");
		AuthorVo vo4 = new AuthorVo("기안84", "기안동에서 산 84년생");
		AuthorVo vo5 = new AuthorVo("강풀", "온라인 만화가 1세대");
		AuthorVo vo6 = new AuthorVo("김영하", "알쓸신잡");
		
		
		authorDao.authorInsert(vo1);
		authorDao.authorInsert(vo2);
		authorDao.authorInsert(vo3);
		authorDao.authorInsert(vo4);
		authorDao.authorInsert(vo5);
		authorDao.authorInsert(vo6);
		

		/*
		authorDao.authorInsert("김문열", "경북 영양");
		authorDao.authorInsert("박경리", "경상남도 통영");
		authorDao.authorInsert("유시민", "17대 국회의원");
		authorDao.authorInsert("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert("김영하", "알쓸신잡");
		*/
		
		//Delete
		//authorDao.authorDelete(5);
		
		
		//Update
		/*
		AuthorVo uVo = new AuthorVo("유재석", "개그맨", 5);
		authorDao.authorUpdate(uVo);
		*/

		//authorDao.authorSelect();
		
		List<AuthorVo> authorList = authorDao.authorSelect();
		for(int i = 0 ; i<authorList.size() ; i++) {
			int authorId = authorList.get(i).getAuthorId();
			String authorName = authorList.get(i).getAuthorName();
			String authorDesc = authorList.get(i).getAuthorDesc();
			
			System.out.println(authorId + ", " + authorName + ", " + authorDesc);
			
			/*
			AuthorVo authorVo = authorList.get(i);
			System.out.println(authorVo.getAuthorId() + ", " + authorVo.getAuthorName() + ", " + authorVo.getAuthorDesc());
			*/
		}
		
	}

}
