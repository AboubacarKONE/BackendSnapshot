import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/notification.service';
import { ResponsableServiceService } from '../Services/responsable-service.service';

@Component({
  selector: 'app-liste-responsable',
  templateUrl: './liste-responsable.component.html',
  styleUrls: ['./liste-responsable.component.scss']
})
export class ListeResponsableComponent implements OnInit {
  listeRespon : any;
  searchText = '';
  constructor(
    private service : ResponsableServiceService,
    private notifyService : NotificationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.listesResponsables();
  }

  async listesResponsables(){
    return this.service.listeResponsable().subscribe((data : any)=>{
      this.listeRespon= data ;   
    })
  }

  deleteResp(data: any){
    this.service.detailResponsable(data).subscribe((datas: any)=>{
      datas.etat = "inactive";
      let datasMod = datas;
      this.service.modifierResponsable(datasMod.id, datasMod).subscribe((mod: any)=>{
        window.location.reload();
      this.router.navigateByUrl('/liste-responsable', {skipLocationChange: true}).then(()=>
      this.router.navigate(['liste-responsable']));
      this.notifyService.showSuccess("Données Supprimer avec succès !!");
      })
    })
  }

}
