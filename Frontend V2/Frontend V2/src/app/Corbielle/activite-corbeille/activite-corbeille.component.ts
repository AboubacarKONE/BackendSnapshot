import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActiviteServiceService } from 'src/app/Activites/Services/activite-service.service';
import { NotificationService } from 'src/app/notification.service';

@Component({
  selector: 'app-activite-corbeille',
  templateUrl: './activite-corbeille.component.html',
  styleUrls: ['./activite-corbeille.component.scss']
})
export class ActiviteCorbeilleComponent implements OnInit {

  listes:any= [];
  searchText= '';

  options: any = {
  confirmBtnClass: 'btn btn-success',      //DEFAULT VALUE
   confirmBtnText: 'Oui',                      //DEFAULT VALUE
   cancelBtnClass: 'btn btn-danger',      //DEFAULT VALUE
   cancelBtnText: 'Non',                      //DEFAULT VALUE
   modalSize: 'lg',                                   //DEFAULT VALUE
   modalClass: ''                                      //DEFAULT VALUE
  }
  

  constructor(
    private notifyService : NotificationService,
    private service : ActiviteServiceService,
    private router : Router,
  ) { }

  ngOnInit(): void {
    this.getAll();
  }

  async getAll(){
    return this.service.getAllList().subscribe((data: any)=>{
      let liste = data;
      
      
      for(let i=0; i<liste.length; i++){
        console.log(liste[i].etat);
        if(liste[i].etat == 'inactive')
        this.listes.push(liste[i]);
      }
    })

  }

  deleteActivivite(data: any){
    this.service.detail(data).subscribe((datas: any)=>{
      datas.etat = "active";
      let datasMod = datas;
      this.service.update(datasMod.id_activite, datasMod).subscribe((mod: any)=>{
        window.location.reload();
      this.router.navigateByUrl('/activite-corbeille', {skipLocationChange: true}).then(()=>
      this.router.navigate(['activite-corbeille']));
      this.notifyService.showSuccess("Données Restauree avec succès !!"); 
      })
    })
  }


}
