package org.example;


import org.example.models.CreditCard;
import org.example.models.User;
import org.example.services.IUserService;
import org.example.services.UserServiceImp;

import static org.example.constants.Constant.sc;

public class Main {


    public static void main(String[] args) {
        IUserService userService = new UserServiceImp();
        User user;
        CreditCard creditCard;


        while (true) {
            System.out.println("-----------WELCOME TO PAYMENT SYSTEM------------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    userService.register();
                    break;
                case 2:
                    System.out.print("Enter your username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter your password: ");
                    String password = sc.nextLine();
                    user = userService.login(username, password);
                    if (user != null) {
                        showMenu();
                    }
                    break;
                default:
                    System.out.println("Exiting...");
            }
            if (choice == 3) {
                break;
            }
        }
    }

    private static void showMenu() {
        while (true) {
            System.out.println("-----------WELCOME TO PAYMENT SYSTEM------------");
            System.out.println("1. Thực hiện thanh toán");
            System.out.println("2. Yêu cầu hoàn tiền");
            System.out.println("3. Báo cáo tài chính");
            System.out.println("4. Cài đặt tài khoản");
            System.out.println("5. Đăng xuất");
            System.out.println("6. Exit");
            System.out.print("Choose your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Exiting...");
            }
            if (choice == 6) {
                break;
            }
        }
    }


}