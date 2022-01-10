import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceAdminService } from 'src/app/Administrateurs/Services/service-admin.service';
import { NotificationService } from 'src/app/notification.service';

@Component({
  selector: 'app-admin-corbeille',
  templateUrl: './admin-corbeille.component.html',
  styleUrls: ['./admin-corbeille.component.scss']
})
export class AdminCorbeilleComponent implements OnInit {
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
          if(listAdmin[i].etat == 'inactive')
          this.listAdmin.push(listAdmin[i]);
        }
      })
  }

  deleteAdmin(data: any){
    this.service.detailAdmin(data).subscribe((datas: any)=>{
      datas.etat = "active";
      let datasMod = datas;
      this.service.updateAdmin(datasMod.id, datasMod).subscribe((mod: any)=>{
        window.location.reload();
      this.router.navigateByUrl('/admin-Corbeille', {skipLocationChange: true}).then(()=>
      this.router.navigate(['admin-Corbeille']));
      this.notifyService.showSuccess("Données Restaurer avec succès !!");
      })
    })
  }
}
