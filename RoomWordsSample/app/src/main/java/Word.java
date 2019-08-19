import androidx.annotation.NonNull;

public class Word {
    private String mWord;

    public Word(@NonNull String word){
        mWord = word;
    }

    public String getWord(){
        return mWord;
    }
}
