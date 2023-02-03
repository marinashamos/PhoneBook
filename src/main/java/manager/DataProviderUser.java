package manager;

import model.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> loginDataCls() {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"dsa@gmail.com", "Qq12345$"});
        list.add(new Object[]{"mjmj@gmail.com", "Qq123456$"});
        list.add(new Object[]{"alex@gmail.com", "Qq12345$"});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginDataUser() {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder().email("dsa@gmail.com").password("Qq12345$").build()});
        list.add(new Object[]{User.builder().email("mjmj@gmail.com").password("Qq123456$").build()});
        list.add(new Object[]{User.builder().email("alex@gmail.com").password("Qq12345$").build()});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginDataUserFromFile() throws IOException {

        List<Object[]> list = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String  line =bufferedReader.readLine();  // "dsa@gmail.com,Qq12345$"
        while(line!=null){
            String[] split = line.split(","); // [0] "dsa@gmail.com" [1] "Qq12345$"
            list.add(new Object[]{User.builder().email(split[0]).password(split[1]).build()});
            line =bufferedReader.readLine(); /// "mjmj@gmail.com,Qq123456$"
        }
        return list.iterator();
    }
}
