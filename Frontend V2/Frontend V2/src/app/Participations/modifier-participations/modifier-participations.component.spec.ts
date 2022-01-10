import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierParticipationsComponent } from './modifier-participations.component';

describe('ModifierParticipationsComponent', () => {
  let component: ModifierParticipationsComponent;
  let fixture: ComponentFixture<ModifierParticipationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifierParticipationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifierParticipationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
