package com.codecafe.healthpulse.controller;

// ─────────────────────────────────────────────────────────────────────────────
// REFERENCE ONLY: Thymeleaf (server-side HTML) controller, basic parameter passing
//
// @Controller      -> returns a VIEW NAME; Thymeleaf renders templates/<name>.html
// @RestController  -> returns DATA (JSON). Both can live in the same project.
//
// Ways to pass data from controller to view:
//   Model / model.addAttribute(k, v)   -> most common
//   ModelAndView                       -> view name + data in one object
//   @ModelAttribute (on a method)      -> adds an attribute to EVERY view in this controller
//   @ModelAttribute (on a parameter)   -> binds submitted form fields into an object
//   RedirectAttributes flash           -> survives one redirect (success messages)
//
// Ways to receive data from the browser:
//   @PathVariable   -> /web/patients/{id}
//   @RequestParam   -> /web/patients?name=john   (query string or form field)
//   @ModelAttribute -> whole form -> Patient object, validated with @Valid + BindingResult
//
// To enable:
//   1. pom.xml -> add spring-boot-starter-thymeleaf
//   2. Create templates in src/main/resources/templates/:
//        patients.html, patient-detail.html, patient-form.html, dashboard.html
//   3. Uncomment everything below.
//   4. SECURITY: a browser can't send a Bearer token when opening a page, so the JWT
//      chain will block /web/**. Either permitAll "/web/**" in SecurityConfig, or
//      switch to BasicSecurityConfig (browser shows a login popup).
// ─────────────────────────────────────────────────────────────────────────────

//import com.codecafe.healthpulse.enums.Acuity;
//import com.codecafe.healthpulse.model.Patient;
//import com.codecafe.healthpulse.service.PatientService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class PatientViewController {
//
//    private final PatientService patientService;
//
//    // @ModelAttribute on a METHOD: runs before every handler, so ${acuities}
//    // is available in all views (used to fill the acuity dropdown)
//    @ModelAttribute("acuities")
//    public Acuity[] acuities() {
//        return Acuity.values();
//    }
//
//    // Model + @RequestParam (optional)
//    // GET http://localhost:9090/web/patients   or   /web/patients?name=john
//    @GetMapping("web/patients")
//    public String list(@RequestParam(required = false) String name, Model model) {
//        List<Patient> patients = (name == null || name.isBlank())
//                ? patientService.findAll()
//                : patientService.searchByName(name);
//        model.addAttribute("patients", patients);
//        model.addAttribute("keyword", name);
//        return "patients";                       // templates/patients.html
//    }
//
//    // @RequestParam with defaultValue
//    // GET http://localhost:9090/web/patients/department?department=Neurology
//    @GetMapping("web/patients/department")
//    public String byDepartment(@RequestParam(defaultValue = "Cardiology") String department, Model model) {
//        model.addAttribute("patients", patientService.findByDepartment(department));
//        model.addAttribute("keyword", department);
//        return "patients";
//    }
//
//    // @PathVariable + Model
//    // GET http://localhost:9090/web/patients/64abc...
//    @GetMapping("web/patients/{id}")
//    public String detail(@PathVariable String id, Model model) {
//        model.addAttribute("patient", patientService.findById(id));
//        return "patient-detail";
//    }
//
//    // Show EMPTY form: the form needs an object to bind to (th:object="${patient}")
//    // (literal path "web/patients/new" wins over "web/patients/{id}")
//    @GetMapping("web/patients/new")
//    public String showForm(Model model) {
//        model.addAttribute("patient", new Patient());
//        return "patient-form";
//    }
//
//    // Form submit: @ModelAttribute binds fields, @Valid checks them,
//    // BindingResult MUST come right after the validated object
//    @PostMapping("web/patients")
//    public String save(@Valid @ModelAttribute("patient") Patient patient,
//                       BindingResult result,
//                       RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            return "patient-form";               // re-show form with error messages
//        }
//        try {
//            patientService.create(patient);
//        } catch (IllegalArgumentException ex) {  // e.g. "MRN already exists"
//            result.rejectValue("mrn", "duplicate", ex.getMessage());
//            return "patient-form";
//        }
//        redirectAttributes.addFlashAttribute("message", "Patient admitted");
//        return "redirect:/web/patients";         // Post-Redirect-Get: avoids double submit
//    }
//
//    // Edit form pre-filled with the existing patient
//    @GetMapping("web/patients/{id}/edit")
//    public String editForm(@PathVariable String id, Model model) {
//        model.addAttribute("patient", patientService.findById(id));
//        return "patient-form";
//    }
//
//    // @PathVariable + @ModelAttribute together
//    @PostMapping("web/patients/{id}")
//    public String update(@PathVariable String id,
//                         @Valid @ModelAttribute("patient") Patient patient,
//                         BindingResult result) {
//        if (result.hasErrors()) {
//            return "patient-form";
//        }
//        patientService.update(id, patient);
//        return "redirect:/web/patients";
//    }
//
//    // HTML forms only support GET/POST, so delete is a POST
//    @PostMapping("web/patients/{id}/delete")
//    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
//        patientService.deleteById(id);
//        redirectAttributes.addFlashAttribute("message", "Patient expunged");
//        return "redirect:/web/patients";
//    }
//
//    // ModelAndView: view name and data in one object (alternative to Model)
//    // GET http://localhost:9090/web/dashboard
//    @GetMapping("web/dashboard")
//    public ModelAndView dashboard() {
//        ModelAndView mav = new ModelAndView("dashboard");
//        mav.addObject("stats", patientService.getDashboardStats());
//        return mav;
//    }
//}

// ─────────────────────────────────────────────────────────────────────────────
// THYMELEAF CHEAT SHEET (what the templates above would contain)
//
// patients.html
//   <p th:if="${message}" th:text="${message}"></p>                    flash message
//   <tr th:each="p : ${patients}">                                     loop
//       <td th:text="${p.mrn}"></td>
//       <td><a th:href="@{/web/patients/{id}(id=${p.id})}">View</a></td>
//   </tr>
//
// patient-form.html
//   <form th:action="@{/web/patients}" th:object="${patient}" method="post">
//       <input type="text" th:field="*{mrn}">
//       <span th:if="${#fields.hasErrors('mrn')}" th:errors="*{mrn}"></span>
//       <select th:field="*{acuity}">
//           <option th:each="a : ${acuities}" th:value="${a}" th:text="${a}"></option>
//       </select>
//       <button type="submit">Save</button>
//   </form>
//
// dashboard.html
//   <h2 th:text="${stats.activeInPatients}"></h2>
// ─────────────────────────────────────────────────────────────────────────────
