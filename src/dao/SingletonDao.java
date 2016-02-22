package dao;
public class SingletonDao{
		private static IEcole ie;		
		static{
			ie =new IEcoleImlp();			
		}
		public static IEcole getIe(){ 
			return ie;
		}
		public static void closeEntityManager(){ 
			ie.close(); 
		}
}
