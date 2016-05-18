package baseClasses;
//import com.sendgrid.*;

public class SendMail {


	  /*public static void main(String[] args) {
		// TODO Auto-generated method stub
		try { send("onkelhoy@gmail.com", "hp222fq@student.lnu.se", "Hello World", "This is the first mail sent with java. <h3>Header</h3><p>and a nice paragraph</p>");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
*/
	public static void send(String to, String from, String subject, String content) throws SendGridException {
		//Henry's API key
		//SendGrid sendgrid = new SendGrid("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");
		 
	    SendGrid.Email email = new SendGrid.Email();
	 
	    email.addTo(to);
	    email.setFrom(from);
	    email.setSubject(subject);
	    email.setHtml(content);
	 
	   // SendGrid.Response response = sendgrid.send(email);
	}
}
