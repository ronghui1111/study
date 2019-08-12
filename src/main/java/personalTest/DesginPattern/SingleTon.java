package personalTest.DesginPattern;

/**
 * @Description 五种单例模式
 * @author rongh
 * @date 2017-08-09 13:28
 * @Copyright: Copyright (c) 2017
 */
public class SingleTon {

	/**
	 * @Description 懒汉模式（慢加载）
	 * @author rongh
	 * @date 2019-08-09 16:26
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	private static SingleTon instanceLazy;

	public static SingleTon getInstanceLazy() {
		if (instanceLazy == null) {
			instanceLazy = new SingleTon();
			return instanceLazy;
		}
		return instanceLazy;
	}

	/**
	 * @Description 饿汉模式
	 * @author rongh
	 * @date 2019-08-09 16:26
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	private static SingleTon instanceHungry = new SingleTon();

	public static SingleTon getInstanceHungry() {
		return instanceHungry;
	}

	/**
	 * @Description 双检法
	 * @author rongh
	 * @date 2019-08-09 14:58
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	private static volatile SingleTon instanceDc;

	public static SingleTon getInstanceDc() {
		if (instanceDc == null) {
			synchronized (SingleTon.class) {
				if (instanceDc == null) {
					instanceDc = new SingleTon();
				}
			}

		}
		return instanceDc;
	}

	/**
	 * @Description 静态内部类
	 * @author rongh
	 * @date 2019-08-09 14:58
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	public static SingleTon getInstanceIn() {
		return InnerSingleTon.instanceIn;
	}

	private static class InnerSingleTon {
		private static SingleTon instanceIn = new SingleTon();

	}

	/**
	 * @Description 枚举法 此方法可以避免应用通过发射或者反序列化注册实例
	 * @author rongh
	 * @date 2019-08-09 14:57
	 * @Copyright: Copyright (c) 2018
	 * @return
	 */
	public static SingleTonEnum getInstance() {
		return SingleTonEnum.INSTANCE_ENUM;
	}

}

enum SingleTonEnum {
	INSTANCE_ENUM;
}
