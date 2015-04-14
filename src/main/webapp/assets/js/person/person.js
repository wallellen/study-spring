;
$(function () {
    (function ($birthday, $form) {
        $('input.alv-datepicker').on("dp.change", function () {
            var date = $(this).datetimePickerInstance().date();
            if (date.isValid()) {
                $birthday.val(date.utc().format());
            }
            $form.data('formValidation').revalidateField('birthday');
        }).datetimepicker(defaultDatePickerOptions)
            .datetimePickerInstance()
            .date(moment($birthday.val()));
    })($('input[name="birthday"]'), $('#form-main'));
});