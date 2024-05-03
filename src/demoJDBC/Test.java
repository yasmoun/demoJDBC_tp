package demoJDBC;

import ma.projet.beans.Client;
import ma.projet.service.ClientService;

public class Test {
	 public static void main(String[] args) {
	        ClientService cs = new ClientService();

	        cs.create(new Client("SAFI", "ali"));
	        cs.create(new Client("ALAOUI", "widane"));
	        cs.create(new Client("RAMI", "amine"));
	        cs.create(new Client("ALAMI", "kamal"));
	        cs.create(new Client("SELAMI", "mohamed"));

	        // Afficher le client dont id = 3
	        Client c = cs.findById(3);
	        if (c != null) {
	            System.out.println(c.getNom() + " " + c.getPrenom());
	        }

	        // Supprimer le client dont id = 3
	        Client clientToDelete = cs.findById(3);
	        if (clientToDelete != null) {
	            cs.delete(clientToDelete);
	        }

	        // Modifier le client dont id = 2
	        Client cc = cs.findById(2);
	        if (cc != null) {
	            cc.setNom("nouveau nom");
	            cc.setPrenom("nouveau Prénom");
	            cs.update(cc);
	        }

	        // Afficher la liste des clients
	        for (Client cl : cs.findAll()) {
	            System.out.println(cl.getNom());
	        }
	    }

}
