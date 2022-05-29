import java.io.IOException;

import static util.utils.split;

public class Test {
    public static void main(String[] args) {
        try{
            split("https://images.unsplash.com/photo-1653420724193-962fe557f1cb?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870", 3, 3);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
