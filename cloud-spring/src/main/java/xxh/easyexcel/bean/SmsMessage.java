package xxh.easyexcel.bean;


public class SmsMessage extends AbstractMessage {

    private static final long serialVersionUID = 2016009420659182944L;

    private String[] content;

    @Override
    public String[] getContent() {
        return content;
    }

    public void setContent(String... content) {
        this.content = content;
    }

    @Override
    public String getType() {
        return MessageTypeConstants.SMS;
    }
}
