import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDishesDemo {
    static List<Dishes> dishlist=new ArrayList<>();
    static List<AlreadyDishes> adlist =new ArrayList<>();
    public static void main(String[] args) {
        Dishes();
        while(true){
            MainMenu();
            Scanner in = new Scanner(System.in);
            int s = in.nextInt();
            switch (s){
                case 1:{                    //1:menu
                    Menu();
                    while (true){
                        int m =in.nextInt();
                        if(m==0){
                            break;
                        }
                        if (m>dishlist.size()){
                            System.out.println("Please enter a correct number!");
                            break;
                        }
                        Dishes dish =dishlist.get(m-1);
                        System.out.println("You ordered 1 "+dish.getDish_name()+",continue or enter 0 to get back");
                        //add to already ordered list
                        adlist.add(new AlreadyDishes(dish.getId(),dish.getDish_name(),dish.getPrice(),1));
                        //if repeat, combine
                        for (int i = 0; i < adlist.size(); i++) {
                            for (int j = i+1; j < adlist.size() ; j++) {
                                if(adlist.get(i).getId()==adlist.get(j).getId()) {
                                    adlist.get(j).setNum(adlist.get(j).getNum()+adlist.get(i).getNum());
                                    adlist.get(j).setPrice(adlist.get(j).getPrice()*adlist.get(j).getNum());
                                    adlist.remove(i);
                                }
                            }
                        }
                    }
                    break;
                }

                case 2:                     //2:already ordered
                    AlreadyDishes();
                    int s1 =in.nextInt();
                    if (s1==0)
                        break;
                case 3:
                    CheckOut();             //3:bill
                    return;
                case 4:                     //4:exit
                    return;
                default:
                    System.out.println("Please enter a correct number!");
            }

        }
    }
    //Dished
    public static void Dishes(){
        Dishes dishes =new Dishes(1,"Steak",8.0);
        dishlist.add(dishes);
        Dishes dishes1 =new Dishes(2,"Chicken",5.0);
        dishlist.add(dishes1);
        Dishes dishes2 =new Dishes(3,"Rice",2.0);
        dishlist.add(dishes2);
        Dishes dishes3 =new Dishes(4,"Veg",3.0);
        dishlist.add(dishes3);
        Dishes dishes4 =new Dishes(5,"Pork",6.0);
        dishlist.add(dishes4);
        Dishes dishes5 =new Dishes(6,"Egg",2.0);
        dishlist.add(dishes5);
    }
    //Main Menu
    public static void MainMenu(){
        System.out.println("-------MainMenu------");
        System.out.println("Menu\t\t\t\t--->1");
        System.out.println("Already Ordered\t\t\t--->2");
        System.out.println("Bill\t\t\t\t--->3");
        System.out.println("Turn off system\t\t\t--->4");
        System.out.println();
        System.out.println("-----------------Please enter a number:");
    }
    //Menu list
    public static void Menu(){
        System.out.println("-------Menu------");
        for (int i = 0; i < dishlist.size(); i++) {
            Dishes dh = dishlist.get(i);
            System.out.println(dh.getId()+"\t"+dh.getDish_name()+"\t"+dh.getPrice());
        }
        System.out.println("-----------------Please enter the number to order~");
        System.out.println("-----------------Enter 0:back to the main menu");
    }
    //Already ordered
    public static void AlreadyDishes(){
        System.out.println("-------Already ordered------");
        for (int i = 0; i < adlist.size(); i++) {
            AlreadyDishes ads =adlist.get(i);
            System.out.println(ads.getId()+"\t"+ads.getDish_name()+"\t"+ads.getPrice()+"\t\t"+ads.getNum());
        }
        System.out.println("-----------------Enter 0:back to the main menu");

    }
    //Bill
    public static void CheckOut(){
        System.out.println("-------Bill------");
        System.out.println();
        //calculate
        double num=0;
        for (int i = 0; i < adlist.size(); i++) {
            AlreadyDishes ads =adlist.get(i);
            num=ads.getPrice()+num;
        }
        System.out.println("Your bill:");
        for (AlreadyDishes ad : adlist) {
            System.out.println(ad.getDish_name()+":"+ad.getNum());
        }
        System.out.println("Total："+num+"$");
    }

}
