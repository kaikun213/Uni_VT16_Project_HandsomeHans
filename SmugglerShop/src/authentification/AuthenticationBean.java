package authentification;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import baseClasses.Page;

public class AuthenticationBean extends Page {
	
	 public static final String AUTH_KEY = "app.user.name";
	 public static boolean admin = false;

	  private String password;
	  private String name;
	  public String getName() { return name; }
	  public void setName(String name) { this.name = name; }
	  public void setPassword(String s){ password = s;}
	  public String getPassword(){ return password;}

	  public boolean isLoggedIn() {
	    return FacesContext.getCurrentInstance().getExternalContext()
	        .getSessionMap().get(AUTH_KEY) != null;
	  }

	  public void login() {
	    Login();
	  }

	  public void logout() {
	    FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	        .remove(AUTH_KEY);
		admin = false;
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect("../mainpage.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	  
	  public void Login(){
			System.out.println("Login Try: " + name + " : " + password);
			if (name.isEmpty() || password.isEmpty()) {
				super.notify("Error","Please enter login data");
			}
			else if ((name.compareTo("root")) == 0 && (password.compareTo("team2") == 0)) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, name);
				admin = true;
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ec.redirect("restricted/adminProducts.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				setContent("SELECT password,admin FROM user WHERE name=\"" + name + "\";");
				if ((getContent(0,"password").compareTo(password) == 0) && (getContent(0,"admin").compareTo("1") == 0)) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, name);
					admin = true;
					ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
					try {
						ec.redirect("restricted/adminProducts.xhtml");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if ((getContent(0,"password").compareTo(password) == 0) && (getContent(0,"admin").compareTo("0") == 0)) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, name);
					ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
					try {
						ec.redirect("userAccount.xhtml");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("not valid:" + getContent(0,"password") + " : " + getContent(0,"admin"));
			}
		}

}
