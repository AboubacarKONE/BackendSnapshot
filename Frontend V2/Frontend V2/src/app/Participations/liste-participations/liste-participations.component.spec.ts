import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeParticipationsComponent } from './liste-participations.component';

describe('ListeParticipationsComponent', () => {
  let component: ListeParticipationsComponent;
  let fixture: ComponentFixture<ListeParticipationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeParticipationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeParticipationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
