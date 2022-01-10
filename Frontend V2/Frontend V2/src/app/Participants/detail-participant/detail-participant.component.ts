import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ParticipantServiceService } from '../Services/participant-service.service';

@Component({
  selector: 'app-detail-participant',
  templateUrl: './detail-participant.component.html',
  styleUrls: ['./detail-participant.component.scss']
})
export class DetailParticipantComponent implements OnInit {

    id: any;
    participant:any;
    participation: any = [];
    nbreParticipation = 0;

  constructor(
    private service: ParticipantServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.service.detailParticipant(this.id).subscribe((data:any) => {
      this.participant = data;

      this.service.listeparticipation().subscribe((dat: any)=>{
        //console.log(this.participant.id_participant);

        for(let i=0; i< dat.length; i ++){
          if(dat[i].participant.id_participant == this.participant.id_participant){
            console.log("ok");
            this.participation.push(dat[i]);
            this.nbreParticipation = this.nbreParticipation + 1
          }else{
            console.log("not ok");

          }
        }
        console.log(this.participation);
      })
    })
  }


}