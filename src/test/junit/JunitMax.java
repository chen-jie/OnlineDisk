package test.junit;

import org.junit.Test;

import cn.od.bean.User;
import cn.od.bean.UserFile;
import cn.od.dao.UserDao;
import cn.od.dao.UserFileDao;

public class JunitMax {

	@Test
	public void testFindUser(){
		UserDao ud = new UserDao();
//		User user = ud.findUser("admin", "admin");
		User user = ud.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testFindUserFile(){
		UserFileDao ud = new UserFileDao();
//		User user = ud.findUser("admin", "admin");
		System.out.println(ud.findUserFileById(1));
	}
	@Test
	public void testUpdateUserFile(){
		UserFileDao ud = new UserFileDao();
		UserFile uf = new UserFile();
		uf.setId(1);
		uf.setFilename("wujiji11.rar");
		uf.setIsShared(0);
		uf.setCount(99);
		ud.update(uf);
		//		User user = ud.findUser("admin", "admin");
		System.out.println();
	}
	@Test
	public void testfindFileListByOwnerId(){
		UserFileDao ud = new UserFileDao();
		System.out.println(ud.findFileListByOwnerId(1));
	}
	
	@Test
	public void testfindSharedfiel(){
		UserFileDao ud = new UserFileDao();
		System.out.println(ud.findSharedFile());
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = new Date();
//		sdf.format(date);
//		
//		System.out.println(new Timestamp(date.getTime()));
	}
	@Test
	public void testInsertString(){
		
		StringBuffer sb = new StringBuffer("abc.html");
		sb.insert(sb.indexOf("."),"(1)");
		System.out.println(sb);
	}
	
	@Test
	public void testDelete(){
		UserFileDao ud = new UserFileDao();
		int[] ids = {1,2};
		ud.deleteByIds(ids);
		System.out.println();
	}
	@Test
	public void testFindByIds(){
		UserFileDao ud = new UserFileDao();
		int[] ids = {11,10};
		
		System.out.println(ud.findByIds(ids));
	}
	@Test
	public void testfindSharedfielwithName(){
		UserFileDao ud = new UserFileDao();
		for(UserFile f : ud.findSharedFileWithName("src"))
			System.out.println(f.getFilename());
		
	}
	
	@Test
	public void testfindUsers(){
		UserDao ud = new UserDao();
		System.out.println(ud.findUsers().size());
		
	}
}
