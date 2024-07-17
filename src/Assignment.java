import java.util.*;

class Car{
int id;
String brand, sponsor, racer;
int roundPosition;
public Car (int id, String brand, String sponsor,String racer){

    this.id=id;
    this.brand=brand;
    this.sponsor=sponsor;
    this.racer=racer;
    this.roundPosition = 0;
   }
}
public class Assignment {
    public static int search(Stack<Car> carStack, int x) {
        Stack<Car> tempStack = new Stack<>();
        int carIndex = -1;

        while (!carStack.isEmpty()) {
            Car car = carStack.pop();
            tempStack.push(car);

            if (car.id == x) {
                carIndex = tempStack.size() - 1; 
                break;
            }
        }

        while (!tempStack.isEmpty()) {
            carStack.push(tempStack.pop());
        }

        return carIndex;
    }
            public static void insertionSortDescending(Stack<Car> carStack) {
           int n = carStack.size();
           Car[] carArray = new Car[n];

           for (int i = n - 1; i >= 0; i--) {
               carArray[i] = carStack.pop();
           }
           for (int i = 1; i < n; i++) {
               Car key = carArray[i];
               int j = i - 1;

               while (j >= 0 && carArray[j].id < key.id) {
                   carArray[j + 1] = carArray[j];
                   j = j - 1;
               }
               carArray[j + 1] = key;
           }
           for (Car car : carArray) {
               carStack.push(car);
           }
       }
    public static void main(String[] args) {
        Scanner objCar = new Scanner(System.in);
        
        LinkedList<Car> carList = new LinkedList<Car>();
        Stack<Car> carStack = new Stack<>();
        
        while (true){
            
            System.out.println("****** ABC  Ltd *******");
            System.out.println("**** CAR RACING EVENT ****");
            System.out.println();
            System.out.println("Please select an option...");
            System.out.println("Option 1: Register Car Details");
            System.out.println("Option 2: Display Car details");
            System.out.println("Option 3: Delete a Car");
            System.out.println("Option 4: Insert round results");
            System.out.println("Option 5: Find out the winners");
            System.out.println("Option 6: Search a particular Car");
            System.out.println("Option 7: Exit");
            System.out.println("Enter your option here: ");
        
            int function = objCar.nextInt();
            objCar.nextLine();
            
            switch (function){
                case 1:
                    
                    System.out.println("Register Car Details");
                    for( int i=0; i<6; i++){
                    System.out.print("Enter Car ID: ");
                    int id = objCar.nextInt();
                    objCar.nextLine();
                    System.out.print("Enter Car Brand: ");
                    String brand = objCar.nextLine();
                    System.out.print("Enter Car Sponsor: ");
                    String sponsor = objCar.nextLine();
                    System.out.print("Enter Car Racer: ");
                    String racer = objCar.nextLine();
                    
                    Car newCar = new Car(id, brand, sponsor, racer);
                    carList.add(newCar);
                    carStack.push(newCar);
                    System.out.println("Car added successfully!"); 
                    }
                    break;
                    
                case 2:
                    System.out.println("Display Car details");
                    insertionSortDescending(carStack);
                    
                    System.out.println("List of Cars in the Stack 'Last registered to first registered' ");
                    for (Car car : carStack) {
                        System.out.println(car.id + " " + car.brand + " " + car.sponsor + " " + car.racer);
                    }
                    break;
                    
                case 3:
                    System.out.println("Delete a Car");
                    System.out.print("Enter Car ID to delete: ");
                    int deleteId = objCar.nextInt();
    
                    boolean carFound = false;

                    Stack<Car> tempStack = new Stack<>();

                    while (!carStack.isEmpty()) {
                       Car car = carStack.pop();
                       if (car.id == deleteId) {
                        carFound = true;
                        System.out.println("Car with ID " + deleteId + " found:");
                        System.out.println("Car ID: " + car.id);
                        System.out.println("Car Brand: " + car.brand);
                        System.out.println("Car Sponsor: " + car.sponsor);
                        System.out.println("Car Racer: " + car.racer);
            
                        System.out.print("Do you want to delete this car? (yes/no): ");
                        String confirmation = objCar.next();
                        if (confirmation.equalsIgnoreCase("yes")) {
                           System.out.println("Car with ID " + deleteId + " has been deleted.");
                        } else {                
                           tempStack.push(car);
                           System.out.println("Car with ID " + deleteId + " was not deleted.");
                               }
                        } else {
                        tempStack.push(car);
                               }
                    }

                    while (!tempStack.isEmpty()) {
                        carStack.push(tempStack.pop());
                    }
                    if (!carFound) {
                        System.out.println("Car with ID " + deleteId + " not found.");
                    }
                    break;
                    
                case 4:
                   System.out.println("Insert Round Results");

                    for (int round = 1; round <= 3; round++) {
                       System.out.println("Enter Round " + round + " Results (Car ID and Position, separated by a space):");

                        for (Car car : carStack) {
                            System.out.print("Car ID " + car.id + ": ");
                            int position = objCar.nextInt();
                            objCar.nextLine();
                            car.roundPosition = position;
                        }

                        carStack.sort(Comparator.comparingInt(c -> c.roundPosition));
                        Car eliminatedCar = carStack.pop();
                        System.out.println("Car " + eliminatedCar.id + " has been eliminated in round " + round);
                    }

                    break;
                case 5:
                    System.out.println("Find out the winners");

                    List<Car> sortedCars = new ArrayList<>(carStack);
                    sortedCars.sort(Comparator.comparingInt(c -> c.roundPosition));

                    if (sortedCars.size() >= 3) {
                        Car firstPlace = sortedCars.get(0);
                        Car secondPlace = sortedCars.get(1);
                        Car thirdPlace = sortedCars.get(2);

                        System.out.println("1st Place: Car ID " + firstPlace.id + " (" + firstPlace.racer + ")");
                        System.out.println("2nd Place: Car ID " + secondPlace.id + " (" + secondPlace.racer + ")");
                        System.out.println("3rd Place: Car ID " + thirdPlace.id + " (" + thirdPlace.racer + ")");
                    } else {
                        System.out.println("No winners yet. Please complete the rounds.");
                    }
                    break;
                case 6:
                    System.out.println("Search a particular Car");
                    System.out.print("Enter Car ID to search: ");
                    int searchId = objCar.nextInt();

                    int searchResult = -1;

                 for (int i = 0; i < carStack.size(); i++) {
                    Car car = carStack.get(i);
                    if (car.id == searchId) {
                    searchResult = i;
                    break;
                    }           
                 }

                 if (searchResult != -1) {
                 Car foundCar = carStack.get(searchResult); 
                 System.out.println("Car with ID " + searchId + " found:");
                 System.out.println("Car ID: " + foundCar.id);
                 System.out.println("Car Brand: " + foundCar.brand);
                 System.out.println("Car Sponsor: " + foundCar.sponsor);
                 System.out.println("Car Racer: " + foundCar.racer);
                 } 
                 else {
                    System.out.println("Car with ID " + searchId + " not found.");
                 }
                    break;
                case 7:
                    System.out.println("Exit");
                    objCar.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
                    
                    
            }
        }
        
    }    
}