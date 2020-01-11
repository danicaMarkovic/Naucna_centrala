import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivateUserAccountComponent } from './activate-user-account.component';

describe('ActivateUserAccountComponent', () => {
  let component: ActivateUserAccountComponent;
  let fixture: ComponentFixture<ActivateUserAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivateUserAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivateUserAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
