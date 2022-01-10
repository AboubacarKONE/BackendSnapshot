import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCorbeilleComponent } from './admin-corbeille.component';

describe('AdminCorbeilleComponent', () => {
  let component: AdminCorbeilleComponent;
  let fixture: ComponentFixture<AdminCorbeilleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminCorbeilleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCorbeilleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
