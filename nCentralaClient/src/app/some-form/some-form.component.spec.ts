import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SomeFormComponent } from './some-form.component';

describe('SomeFormComponent', () => {
  let component: SomeFormComponent;
  let fixture: ComponentFixture<SomeFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SomeFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SomeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
