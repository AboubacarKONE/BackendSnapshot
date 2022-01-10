import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Administrateur } from 'src/app/Models/Administrateur';
import { Role } from 'src/app/Models/Role';
import { NotificationService } from 'src/app/notification.service';
import { RoleServiceService } from 'src/app/Role/Services/role-service.service';
import { ServiceAdminService } from '../Services/service-admin.service';

@Component({
  selector: 'app-ajout-admin',
  templateUrl: './ajout-admin.component.html',
  styleUrls: ['./ajout-admin.component.scss']
})
export class AjoutAdminComponent implements OnInit {
  listeRole : any;
  admin = new Administrateur();
  role: any;
  adm: any;
  idRole: any;

  nom: any;
  prenom: any;
  login: any;
  password: any;
  telephone: any;
  email: any;
  etat: any;

  constructor(
    private serviceRole : RoleServiceService,
    private serviceAdmin: ServiceAdminService,
    private notifyService : NotificationService,
    private router: Router,
  ) 
  {
    this.serviceRole.listeRole().subscribe((data:any)=>{
      this.listeRole = data;
    });;
   }

  ngOnInit(): void {}

  ajoutAdmin(form : NgForm){

    this.idRole = form.value.role;
    this.serviceRole.detailRole(this.idRole).subscribe((data: any)=>{
      this.role = data;
      console.log("==================",this.role);
 
       this.nom = form.value['nom'];
       this.prenom = form.value['prenom'];
       this.telephone = form.value['telephone'];
       this.email = form.value['email'];
       this.etat = "active";
       this.login = form.value['login'];
       this.password = "Orange"+ this.telephone.substr(0, 3);
 
       this.admin.nom= this.nom;
       this.admin.prenom= this.prenom;
       this.admin.telephone= this.telephone;
       this.admin.email= this.email;
       this.admin.etat= this.etat;
       this.admin.login= this.login;
       this.admin.password= this.password;
       this.admin.role= this.role;
       this.serviceAdmin.ajoutAdmin(this.admin).subscribe((data: any)=>{
         this.adm = data;
         console.log("-----------", this.adm);
         this.router.navigate(['liste-admin']);
         this.notifyService.showSuccess("Données enregistrée avec succès !!");
       })
    });
   }
}
