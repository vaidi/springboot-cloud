package xxh.easyexcel.bean;

import java.io.Serializable;

/**
 * <p>
 * 用于维持java类的子类信息，将子类对象类型信息嵌入到json中，以便反序列化创建具体的对象。
 * </p>
 *
 * @author kkb
 */
public abstract class AbstractMessage implements KkbMessage, Serializable {

    private static final long serialVersionUID = 7232142528102064659L;
    private String template;

    protected String recipient;

    @Override
    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String getTemplate() {
        return this.template;
    }

    @Override
    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }


}
