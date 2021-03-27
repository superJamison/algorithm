package com.jms;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/17 21:31
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //
        ArrayList<Person> personArrayList = new ArrayList<>(15);
        for (int i = 0; i < 15; i++) {
            personArrayList.add(new Person("jamison", i + 6));
        }
        System.out.println(personArrayList);
        personArrayList.remove(0);
        System.out.println(personArrayList);
        System.out.println(personArrayList.contains(new Person("jamison", 7)));
        /*Person person = new Person("zms", 11);
        personArrayList.add(person);
        System.out.println(personArrayList);
//        System.out.println(personArrayList.size());
        System.out.println(personArrayList.indexOf("int--->3"));*/
//        personArrayList.clear();
//        System.gc();
    }

    public void test() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 22; i++) {
            arrayList.add(i + 1);
        }
        System.out.println(arrayList);
        arrayList.add(arrayList.size(), 22);
        System.out.println(arrayList.set(1, 99));
        System.out.println(arrayList);
        arrayList.remove(arrayList.size() - 1);
        System.out.println(arrayList);
        System.out.println(arrayList.get(1));
        arrayList.clear();
        System.out.println(arrayList);
        arrayList.add(100);
        System.out.println(arrayList);
    }
}
