import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Prg_toy {
    public static List <Toys> makeSet(List <Toys> toys, Integer id, String toy_name, Integer quantity,Integer weight) {
        
        Toys n = new Toys();
        n.id = id;
        n.toy_name = toy_name;
        n.quantity = quantity;
        n.weight = weight;
                   
        toys.add(n);

        return toys;
    }

    public static Integer play(List <Toys> toys) {
        while (true) {
            Integer total = 0;
            ArrayList <Integer> weights = new ArrayList <>();

            for (Toys toy : toys) {
                total = total + IntStream.of(toy.weight).sum();    
                for (int i = 0; i < toy.weight; i++) {
                    weights.add(toy.id);    
                }
            }
            Collections.shuffle(weights);
            Integer random = (int) (Math.random() * total);
            Integer id = weights.get(random);
            for (Toys toy : toys) {
                if (id==toy.id && toy.quantity>0) {
                    return id;
                }
            }
            
        }
    }

    public static void writeToFile(String toy_name, Integer toy_id) {
        try(FileWriter writer = new FileWriter("win.txt", true))
        {
            writer.write("Toy: " + toy_name + ", number id = " + toy_id+ "\n");
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }     
    }

    public static void print_list(List <Toys> toys) {
        if (toys.size()>0) {
            for (Toys toy : toys) {
                System.out.println(toy);    
            } 
        } else {
            System.out.println("Список пуст...");  
        }
    }

    public static void filter(List <Toys> toys, List <Toys> plays) {
        while (true) {
            System.out.println("\nПрограмма розыгрыша игрушек \nВыберите цифру для: \n   1 - Добавление новой игрушки \n   2 - Изменение веса игрушки \n   3 - Удаление игрушки \n   4 - Розыгрыш приза\n   5 - Получить приз\n   6 - Вывести полный список\n   7 - Выход\n");
            Scanner sc = new Scanner(System.in);
            switch (sc.nextInt()) {
                case (1) :
                    System.out.println("Добавление новой игрушки: ");
                    System.out.println("Введите id: ");
                    int res=0;
                    Integer id=0;
                    if (toys.size()>0) {
                        while (res==0) {
                            id = sc.nextInt();  
                            for (Toys toy : toys) {
                                if (id==toy.id) {
                                    System.out.println("Такой id уже есть. Введите новый: ");    
                                    res=0;
                                    break;
                                } else {
                                res=1;
                                }
                            }
                        }
                    } else {
                        id = sc.nextInt(); 
                    }
                    System.out.println("Введите название: ");
                    String toy_name = sc.next();  
                    System.out.println("Введите количество: ");
                    Integer quantity= sc.nextInt();     
                    System.out.println("Введите вес: ");
                    Integer weight= sc.nextInt();   
                    toys = makeSet(toys, id, toy_name, quantity, weight);  
                    break;
                case (2) :
                    System.out.println("Изменение веса игрушки: ");
                    System.out.println("Введите id игрушки: ");
                    if (toys.size()>0) {
                        id = sc.nextInt();  
                        for (Toys toy : toys) {
                            if (id==toy.id) {
                                System.out.println("Введите вес игрушки: ");
                                weight = sc.nextInt();
                                toy.setWeight(weight);
                                System.out.println("Вес игрушки изменен.");
                            }
                        }
                    } else {
                        System.out.println("Список пуст...");
                    }
                    break;
                case (3) :
                    System.out.println("Удаление игрушки: ");
                    System.out.println("Введите id игрушки: ");
                    if (toys.size()>0) {
                        id = sc.nextInt();  
                        for (Toys toy : toys) {
                            if (id==toy.id) {
                                toys.remove(toy);
                                System.out.println("Игрушка удалена.");
                                break;
                            }
                        }
                    } else {
                        System.out.println("Список пуст...");
                    }
                    break;
                case (4) :
                    System.out.println("Розыгрыш: ");
                    
                    if (toys.size()>0) {
                        id=play(toys);
                        for (Toys toy : toys) {
                            if (id==toy.id && toy.quantity>0) {
                                System.out.println("Игрушка найдена: " + toy.toy_name);    
                                if (toy.getQuant()>1) {
                                    toy.setQuant(toy.quantity-1);
                                } else {
                                    toys.remove(toy);
                                }
                                plays.add(toy);
                                System.out.println("Выигранные призы: ");
                                print_list(plays);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Призы закончились... (");
                    }
                    break;
                case (5) : 
                    System.out.println("Выигранные призы: ");
                    print_list(plays);
                    if (plays.size()!=0) {
                        System.out.println("Получить приз: \n" + plays.get(0));
                        writeToFile(plays.get(0).toy_name,plays.get(0).id);
                        plays.remove(0);}
                    else {
                        System.out.println("Все призы получены. ");
                        break;
                    }
                    System.out.println("Оставшиеся призы: ");
                    print_list(plays);
                    break;
                case (6) :
                    System.out.println("Полный список: \n");
                    print_list(toys);
                    break;
                case (7) :
                    System.out.println("До встречи! \n");
                    System.exit(0);
                default :
                    System.out.println("Неправильный ввод..");
            }
        }
    }

    public static void main(String[] args) {
        List <Toys> toys = new LinkedList<Toys>(); 
        List <Toys> plays = new LinkedList<Toys>();

        toys = makeSet(toys, 1, "Doll Sasha", 2, 10);
        toys = makeSet(toys, 2, "Doll Dasha", 2, 10);
        toys = makeSet(toys, 3, "Car", 1, 15);
        toys = makeSet(toys, 4, "Tractor", 1, 20);
        toys = makeSet(toys, 5, "Soft toy Begemot", 4, 45);

        filter(toys, plays);
    }
}
