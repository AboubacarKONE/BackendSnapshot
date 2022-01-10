import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/notification.service';
import { ActiviteServiceService } from '../Services/activite-service.service';

@Component({
  selector: 'app-liste-activite',
  templateUrl: './liste-activite.component.html',
  styleUrls: ['./liste-activite.component.scss']
})
export class ListeActiviteComponent implements OnInit {
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
        if(liste[i].etat == 'active')
        this.listes.push(liste[i]);
      }
    })

  }

  deleteActivivite(data: any){
    this.service.detail(data).subscribe((datas: any)=>{
      datas.etat = "inactive";
      let datasMod = datas;
      this.service.update(datasMod.id_activite, datasMod).subscribe((mod: any)=>{
        window.location.reload();
      this.router.navigateByUrl('/liste-activite', {skipLocationChange: true}).then(()=>
      this.router.navigate(['liste-activite']));
      this.notifyService.showSuccess("Données Supprimer avec succès !!"); 
      })
    })
  }

  getExercice(date:any) {
    this.service.exercice(date.year).subscribe((data)=> {
      this.listes = data;
      console.log(data);
    })
  }

  getMonth(date:any) {
    this.service.Month(date.mois).subscribe((data)=> {
      this.listes = data;
      console.log(data);
    })
  }

  confirmed() {
    console.log('confirmed');
   }
  
   cancelled() {
    console.log('cancelled');
   }
}