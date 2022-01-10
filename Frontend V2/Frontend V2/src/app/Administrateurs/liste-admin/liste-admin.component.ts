import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/notification.service';
import { ServiceAdminService } from '../Services/service-admin.service';

@Component({
  selector: 'app-liste-admin',
  templateUrl: './liste-admin.component.html',
  styleUrls: ['./liste-admin.component.scss']
})
export class ListeAdminComponent implements OnInit {
  listAdmin: any=[];
  searchText= '';

  constructor(
    private service : ServiceAdminService,
    private notifyService : NotificationService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.listeAdm();
  }

  async listeAdm(){
      return this.service.listeAdmin().subscribe((data: any)=>{
        let listAdmin = data;
        for(let i=0; i<listAdmin.length; i++){
          console.log(listAdmin[i].etat);
          if(listAdmin[i].etat == 'active')
          this.listAdmin.push(listAdmin[i]);
        }
      })
  }

  deleteAdmin(data: any){
    this.service.detailAdmin(data).subscribe((datas: any)=>{
      datas.etat = "inactive";
      let datasMod = datas;
      this.service.updateAdmin(datasMod.id, datasMod).subscribe((mod: any)=>{
        window.location.reload();
      this.router.navigateByUrl('/liste-admin', {skipLocationChange: true}).then(()=>
      this.router.navigate(['liste-admin']));
      this.notifyService.showSuccess("Données Supprimer avec succès !!");
      })
    })
  }
}
