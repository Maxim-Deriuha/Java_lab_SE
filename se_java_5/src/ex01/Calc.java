
package ex01;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/** Содержит реализацию методов для вычисления и отображения результатов.
* @author xone
* @version 1.0
*/
public class Calc {
	/** �?мя файла, используемое при сериализации. */
//	private static final String FNAME = "Item2d.bin";
	
	/** Сохраняет результат вычислений. Объект класса {@linkplain Item2d} */
	private double result;
	
	/** �?нициализирует {@linkplain Calc#result} */
	public Calc() {
		result = 0;
	}
	
	/** Установить значение {@linkplain Calc#result}
	* @param result - новое значение ссылки на объект {@linkplain Item2d}
	*/
	public void setResult(double result) {
		this.result = result;
	}
	/** Получить значение {@linkplain Calc#result}
	* @return текущее значение ссылки на объект {@linkplain Item2d}
	*/
	public double getResult() {
		return result;
	}
	
	/** Вычисляет значение функции.
	* @param x - аргумент вычисляемой функции.
	* @return результат вычисления функции.
	*/
	private double iterated_bitcount (int number)
	{
		int count=0;
    	while (number != 0)
    	{
        	count += number & 0x1 ;
        	number >>= 1 ;
    	}
    	return count;
		
	}
	
	private double calc(int[] numbers) {
		double number = 0;
		int num = 0;
		for(int i = 0; i < 4; i++) num += 1000 * (int)Math.sin(numbers[i]);
	    num /= 4;
		for(int i = 0; i < 4; i++) number = iterated_bitcount (numbers[i]);
		return number;
	    
	}


	/** Вычисляет значение функции и сохраняет
	* результат в объекте {@linkplain Calc#result}
	* @param x - аргумент вычисляемой функции.
	*/

	public double init(double x) {
		int [] numbers = new int[4];
		for(int i = 0; i < 4; i++) {
			numbers[i] = (int)x + 1;
		}
		return result = calc(numbers);
	}

	/** Выводит результат вычислений. */
	public void show() {
		System.out.println(" " + result);
	}
	
//	/** Сохраняет {@linkplain Calc#result} в файле {@linkplain Calc#FNAME}
//	* @throws IOException
//	*/
//	public void save() throws IOException {
//		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
//		os.writeObject(result);
//		os.flush();
//		os.close();
//	}
//
//	/** Восстанавливает {@linkplain Calc#result} из файла {@linkplain Calc#FNAME}
//	* @throws Exception
//	*/
//	public void restore() throws Exception {
//		ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
//		result = (double)is.readObject();
//		is.close();
//	}
}
