import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/notification.service';
import { ExerciceServiceService } from '../Services/exercice-service.service';

@Component({
  selector: 'app-liste-exercice',
  templateUrl: './liste-exercice.component.html',
  styleUrls: ['./liste-exercice.component.scss']
})
export class ListeExerciceComponent implements OnInit {
  listeExo : any=[];
  searchText= '';
  constructor(
    private service: ExerciceServiceService,
    private notifyService : NotificationService,
    private router: Router
  ) { }

  ngOnInit(): void {
      //lancer directement la fonction listeExercices a l'ouverture de la page
      this.listeExercices();
  }
  // Recuperation des données qui se trouve dans la fonction listeExercice par la fonction listeExercices et l'affecter a listeExo
  listeExercices(){
    this.service.listeExercice().subscribe((data:any)=>{
      let listeExo = data;
      for(let i=0; i<listeExo.length; i++){
        console.log(listeExo[i].etat);
        if(listeExo[i].etat == 'active')
        this.listeExo.push(listeExo[i]);
      }
    })
  }

  deleteExercice(data: any){
    
  this.service.detailExercice(data).subscribe((datas: any)=>{
    datas.etat = "inactive";
    let datasMod = datas;
    this.service.updateExercice(datasMod.id, datasMod).subscribe((mod: any)=>{
      window.location.reload();
    this.router.navigateByUrl('/liste-exercice', {skipLocationChange: true}).then(()=>
    this.router.navigate(['liste-exercice']));
    this.notifyService.showSuccess("Données Supprimer avec succès !!"); 
    })
  })

  }

}
