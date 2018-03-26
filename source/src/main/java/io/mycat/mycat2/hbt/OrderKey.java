package io.mycat.mycat2.hbt;

import java.util.List;

import javax.swing.SortOrder;

/**
 *  排序的字段的
 * */
public class OrderKey implements Comparable<OrderKey> {
	/*排序的字段的值*/
	List<String> fieldNameList = null;
	/*某個字段是升序 還是降序*/
	private List<SortOrder> sortOrderList = null;
	/*index 表示的是在原始的位置的索引(標記) */
	int index  = 0;
	
	public OrderKey(List<String> list, 
			List<SortOrder> sortOrderList, 
			int index) {
		this.fieldNameList = list;
		this.sortOrderList = sortOrderList;
		this.index = index;
	}
	

	public void print() {
		fieldNameList.stream().forEach(val -> {
			System.out.print(val + "  ");
		});

		System.out.println("==========" + index);
	}

	
	@Override
	public int compareTo(OrderKey o) {
		int result = 0;
		for(int i = 0 ; i < fieldNameList.size(); i++) {
			String val1 = fieldNameList.get(i);
			String val2 = o.fieldNameList.get(i);
			if(null != val1 && val2 != null) {
				result = val1.compareTo(val2);
			} else if(val1 == null && val2 != null) {
				result = -1;
			} else if(val1 != null && val2 == null) {
				result = 1;
			}
			if(result != 0) {
				return sortOrderList.get(i).equals(SortOrder.ASCENDING)? result : -1 * result;
			}
		}
		return result;
	}


	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
//	public static void main(String[] args) {
//		List<SortOrder> sortList = Arrays.asList(SortOrder.ASCENDING,SortOrder.DESCENDING,SortOrder.ASCENDING ,SortOrder.DESCENDING);
//		List<OrderKey> OrderKeys = Arrays.asList(
//					new OrderKey(Arrays.asList("1","2","3","4"),sortList, 0),
//					new OrderKey(Arrays.asList("2","1","5","5"),sortList, 1),
//					new OrderKey(Arrays.asList("3","3","1","6"),sortList, 2),
//					new OrderKey(Arrays.asList(null,"4","2","7"),sortList, 3),
//					new OrderKey(Arrays.asList("1","3","4","1"),sortList, 4),
//					new OrderKey(Arrays.asList("1","2","2","2"),sortList, 5),
//					new OrderKey(Arrays.asList("1",null,"1","1"),sortList, 6)
//				);
//		Collections.sort(OrderKeys);
//		OrderKeys.stream().forEach(OrderKey::print);
//	}
}
