import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Agent
{
    String agentID,name,email,password;
    List<String> contactNumber;
    int totalRented,totalSold;

    public Agent(String agentID, String name, String email, String password)
    {
        this.agentID = agentID;
        this.name = name;
        this.email = email;
        this.password = password;
        contactNumber = new ArrayList<>();
        totalRented = 0;
        totalSold = 0;
    }

    public String toString()
    {
        return agentID+" "+name+" "+email+" "+password+" "+totalRented+" "+totalSold+" "+ Arrays.toString(new List[]{contactNumber});
    }
}
