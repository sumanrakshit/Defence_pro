import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswrdComponent } from './passwrd.component';

describe('PasswrdComponent', () => {
  let component: PasswrdComponent;
  let fixture: ComponentFixture<PasswrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PasswrdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PasswrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
