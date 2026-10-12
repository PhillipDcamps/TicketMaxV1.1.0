package app.view;

import app.domain.User;
import app.domain.enums.SelectPreferencesEnum;
import app.service.helpers.SetUserState;
import app.service.inputports.UserService;
import app.service.validators.DataTypeValidator;
/**import app.domain.User;**/

/**import java.util.ArrayList;**/
/**import java.util.List;**/

public class UserView {


    private final UserService userService;

    public UserView(UserService userService){
        this.userService = userService;
    }

    public void createUser() {


        int id = DataTypeValidator.validateInt("Ingrese el id del usuario: ");
        String name = DataTypeValidator.validateString("Ingrese el nombre del usuario: ");
        String lastName = DataTypeValidator.validateString("Ingrese el apellido del usuario: ");
        String email = DataTypeValidator.validateString("Ingrese el correo del usuario: ");
        String phone = DataTypeValidator.validateString("Ingrese el telefono del usuario: ");
        String password = DataTypeValidator.validateString("Ingrese la contraseña del usuario: ");
        System.out.println("Ingrese el estado del usuario: ");
        String state = SetUserState.getUserState();
        System.out.println();
        String city = DataTypeValidator.validateString("Ingrese la ciudad del usuario: ");
        System.out.println("Ingrese las preferencias del usuario: ");
        String preferences = setUserPreferences();

        userService.create(id, name , lastName , email , phone , password , state, city, preferences);

    }

    // imprimir datos

    private void printUser(User user){
        System.out.println("id: " + user.getId());
        System.out.println("Nombre: " + user.getName() + " " + user.getLastName());
        System.out.println("Correo: " + user.getEmail());
        System.out.println("Telefono: " + user.getPhone());
        System.out.println("Estado: " + user.isState());
        System.out.println("Ciudad: " + user.getCity());
        System.out.println("Preferencias: " + user.getPreferences());
    }


    public void selectUserById(int id) {
        User user = userService.selectUserById(id);
        if (user == null){
            System.out.println("No existe un usuario con Id " + id);
            return;
        }

        printUser(user);
    }


    public void selectUsers(){ //selectUser()

        userService.selectUsers();

    }


    public void updateUser() {
    int id = DataTypeValidator.validateInt("Ingrese el id del usuario a actualizar: ");

    if (userService.selectUserById(id)== null){
        System.out.println("No existe un usuario con id " + id);
        return;
    }

    String name = DataTypeValidator.validateString("Ingrese el nombre del usuario: ");
    String lastName = DataTypeValidator.validateString("Ingrese el apellido del usuario: ");
    String email = DataTypeValidator.validateString(("Ingrese el email del usuario: "));
    String phone = DataTypeValidator.validateString("Ingrese el telefono del usuario: ");
    String password = DataTypeValidator.validateString("Ingrese contraseña del usuario: ");
        System.out.println("Ingrese el estado de la cuenta: ");
        String state = SetUserState.getUserState();
    String city = DataTypeValidator.validateString("Ingrese la ciudad del usuario: ");
        System.out.println("Ingrese las preferencias del usuario: ");
        String preferences = setUserPreferences();

        User updated = userService.updateUser(id, name, lastName, email, phone, password, state,city, preferences);

        if (updated != null){
            System.out.println("Usuario actualizado correctamente");
            printUser(updated);
        }
    }

    public void deleteUser(int id){
        if (userService.deleteUser(id)){
            System.out.println("Usuario con id " + id + " eliminado correctamente");
        } else {
            System.out.println("No existe un usuario con id " + id);
        }
    }

    public void countUsers(){
        System.out.println("Total de usuarios registrados: " + userService.countUsers());
    }



    // métodos Helper




    public String setUserPreferences(){


        int option = DataTypeValidator.validateInt("""
                Seleccione:
                  1. VIP 
                  2. General 
                  3. Preferencial""");
        String preferences = "";

        switch (option){

            case 1:
                preferences = SelectPreferencesEnum.VIP.getPreference();
                break;
            case 2:
                preferences = SelectPreferencesEnum.GENERAL.getPreference();
                break;
            case 3:
                preferences = SelectPreferencesEnum.PREFERENCIAL.getPreference();
                break;
            default:
                System.out.println("Opción no valida");

        }
        return preferences;
    }








}
