import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExerciceServiceService } from 'src/app/Exercices/Services/exercice-service.service';
import { NotificationService } from 'src/app/notification.service';

@Component({
  selector: 'app-exercice-corbeille',
  templateUrl: './exercice-corbeille.component.html',
  styleUrls: ['./exercice-corbeille.component.scss']
})
export class ExerciceCorbeilleComponent implements OnInit {
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
        if(listeExo[i].etat == 'inactive')
        this.listeExo.push(listeExo[i]);
      }
    })
  }

  deleteExercice(data: any){
    
  this.service.detailExercice(data).subscribe((datas: any)=>{
    datas.etat = "active";
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
