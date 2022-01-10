import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { Exercice } from 'src/app/Models/Exercice';
import { NotificationService } from 'src/app/notification.service';
import { ExerciceServiceService } from '../Services/exercice-service.service';

@Component({
  selector: 'app-ajout-exercice',
  templateUrl: './ajout-exercice.component.html',
  styleUrls: ['./ajout-exercice.component.scss']
})
export class AjoutExerciceComponent implements OnInit {
  exercice : any;
  exo = new Exercice();
  erreur = '';
  erreurIsExo='';
  isExo: any;
  constructor(
    private service : ExerciceServiceService,
    private router: Router,
    private notifyService : NotificationService,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
  }

  ajoutExercice(form: NgForm){
    if(form.valid){

      if(moment(form.value['date_debut']).isBefore(form.value['date_fin'])){
        this.exo.annee = form.value['annee'];
        this.exo.date_debut = form.value['date_debut'];
        this.exo.date_fin = form.value['date_fin'];
        this.exo.statut = 'encours';
        this.exo.etat = 'active';
        this.service.RechercheExercice(this.exo.annee).subscribe((data: any)=>{
          this.isExo = data;
          console.log(this.isExo);

          if(this.isExo.length != 0){
            this.showToast();
          }else{
             this.service.ajoutExercice(this.exo).subscribe((data: any)=>{
             this.exercice = data;
             this.router.navigate(['liste-exercice']);
             this.notifyService.showSuccess("Données enregistrée avec succès !!");
             });
          }

        })

    }else{
      this.showToas();
    }
    }
  }

  showToas() {
    this.toast.error('La date de debut doit être inferieure à la date de fin')

  }

  showToast() {
    this.toast.error("Cet exercice existe déjà !")
  }

}